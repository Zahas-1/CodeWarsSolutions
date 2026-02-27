public class GetSum
{
    public int GetSum(int a, int b)
    {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        return (min + max) * (max - min + 1) / 2;
    }
}