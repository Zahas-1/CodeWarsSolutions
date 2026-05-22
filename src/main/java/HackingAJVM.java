// Allows this JVM to connect to and control another running JVM process
// using the Java Attach API.
import com.sun.tools.attach.VirtualMachine;

// Imports all classes from java.io such as InputStream.
import java.io.*;

// Gives access to the Java Instrumentation API.
// This API is used by Java agents to inspect or modify classes at runtime.
import java.lang.instrument.Instrumentation;

// Imported but actually unused in this solution.
// Could be used to get information about the current JVM.
import java.lang.management.ManagementFactory;

// Utility classes for working with files and paths.
import java.nio.file.Files;
import java.nio.file.Path;

// Classes used for dynamically building a JAR file in memory/on disk.
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

// Main class containing the JVM hacking logic.
public class HackingAJVM {

    // Shared variable that can safely be updated across threads.
    // "volatile" ensures visibility of updates between threads.
    private static volatile String code;

    // Special method used by Java agents.
    // This method is automatically executed INSIDE the target JVM
    // after the agent is attached.
    public static void agentmain(String args, Instrumentation inst) {

        try {

            // Dynamically loads the class:
            // Burglary$Target
            //
            // '$' is used because Burglary$Target is an inner class.
            Class<?> target = Class.forName("Burglary$Target");

            // Retrieves the private field called "securityCode".
            var field = target.getDeclaredField("securityCode");

            // Disables Java access checks so private fields can be accessed.
            field.setAccessible(true);

            // Reads the static field value from the class.
            //
            // Because the field is static, we pass null instead of an object.
            code = (String) field.get(null);

            // Writes the extracted security code into a temporary file.
            //
            // "args" contains the file path passed into loadAgent().
            Files.writeString(Path.of(args), code);

        } catch (Exception e) {

            // Wraps any checked exception into an unchecked RuntimeException.
            throw new RuntimeException(e);
        }
    }

    // Main method called by the Codewars tests.
    // Receives the PID (process ID) of the target JVM.
    public String guessSecurityCode(final String pid) throws Exception {

        // Creates a temporary file where the target JVM
        // will write the stolen security code.
        Path out = Files.createTempFile("security", ".txt");

        // Dynamically builds a Java Agent JAR file.
        Path agent = createAgentJar();

        // Attaches to the external JVM process using its PID.
        VirtualMachine vm = VirtualMachine.attach(pid);

        // Loads the generated Java agent into the target JVM.
        //
        // First argument:
        //   path to the agent JAR
        //
        // Second argument:
        //   string passed into agentmain() as "args"
        //   here it is the temp file path.
        vm.loadAgent(agent.toString(), out.toString());

        // Disconnects from the target JVM.
        vm.detach();

        // Reads and returns the stolen security code
        // from the temporary file.
        return Files.readString(out);
    }

    // Creates a valid Java Agent JAR file dynamically at runtime.
    private static Path createAgentJar() throws Exception {

        // Creates a new JAR manifest.
        Manifest manifest = new Manifest();

        // Retrieves the manifest's main attribute section.
        Attributes attrs = manifest.getMainAttributes();

        // Required manifest version.
        attrs.put(Attributes.Name.MANIFEST_VERSION, "1.0");

        // Specifies which class contains the agent entry point.
        //
        // Java will call:
        // HackingAJVM.agentmain(...)
        attrs.put(
                new Attributes.Name("Agent-Class"),
                HackingAJVM.class.getName()
        );

        // Declares that this agent can redefine classes at runtime.
        attrs.put(
                new Attributes.Name("Can-Redefine-Classes"),
                "true"
        );

        // Declares that this agent can retransform classes.
        attrs.put(
                new Attributes.Name("Can-Retransform-Classes"),
                "true"
        );

        // Creates a temporary JAR file on disk.
        Path jar = Files.createTempFile("agent", ".jar");

        // Opens a JarOutputStream to write into the JAR file.
        //
        // try-with-resources ensures it closes automatically.
        try (JarOutputStream jos =
                     new JarOutputStream(
                             Files.newOutputStream(jar),
                             manifest
                     )) {

            // Converts the class name into a JVM resource path.
            //
            // Example:
            // HackingAJVM -> HackingAJVM.class
            //
            // If package existed:
            // com.example.Test ->
            // com/example/Test.class
            String classFile =
                    HackingAJVM.class.getName()
                            .replace('.', '/')
                            + ".class";

            // Creates a new entry inside the JAR file
            // representing the compiled class file.
            jos.putNextEntry(new JarEntry(classFile));

            // Opens the current class bytecode as an InputStream.
            try (InputStream in =
                         HackingAJVM.class
                                 .getClassLoader()
                                 .getResourceAsStream(classFile)) {

                // Copies the compiled class bytes into the JAR.
                jos.write(in.readAllBytes());
            }

            // Finishes the current JAR entry.
            jos.closeEntry();
        }

        // Returns the path to the generated agent JAR file.
        return jar;
    }
}