import java.util.ArrayList;
import java.util.List;

class Pair
{
	int first, second;

	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
}

class Main
{
	public static  List<Pair> constructPath(int L[][], int[][] grid,
														Pair tail)
	{
		List<Pair> path = new ArrayList<>();
		path.add(tail);

		int i = tail.first;
		int j = tail.second;

		while (L[i][j] != 0)
		{
			if (i - 1 >= 0 && L[i][j] - L[i - 1][j] == 1
					&& Math.abs(grid[i - 1][j] - grid[i][j]) == 1)
			{
				path.add(new Pair(i - 1, j));
				i--;
			}

			else if (j - 1 >= 0 && (L[i][j] - L[i][j - 1] == 1)
					&& Math.abs(grid[i][j - 1] - grid[i][j]) == 1)
			{
				path.add(new Pair(i, j - 1));
				j--;
			}
		}

		return path;
	}

	public static List<Pair> findMaxLengthSnakeSequence(int[][] grid)
	{
        int[][] L = new int[grid.length][grid.length];  
		int max_so_far = 0;
		Pair tail = null;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				L[i][j] = 0;
				if (i-1 >= 0 && Math.abs(grid[i - 1][j] - grid[i][j]) == 1) {
					L[i][j] = L[i - 1][j] + 1;
					if (max_so_far < L[i][j]) {
						max_so_far = L[i][j];
						tail = new Pair(i, j);
					}
				}
				if (j-1 >= 0 && Math.abs(grid[i][j - 1] - grid[i][j]) == 1) {
					L[i][j] = Integer.max(L[i][j], L[i][j - 1] + 1);
					if (max_so_far < L[i][j])
					{
						max_so_far = L[i][j];
						tail = new Pair(i, j);
					}
				}
			}
		}
		return constructPath(L, grid, tail);
	}

	public static void main(String[] args)
	{
		int[][] grid =
		{
			{ 7, 5, 2, 3, 1 },
			{ 3, 4, 1, 4, 4 },
			{ 1, 5, 6, 7, 8 },
			{ 3, 4, 5, 8, 9 },
			{ 3, 2, 2, 7, 6 }
		};

		List<Pair> path = findMaxLengthSnakeSequence(grid);

		System.out.print("Maximum length Snake sequence : ");

		for (int i = path.size() - 1; i >= 0; i--) {
			System.out.print(grid[path.get(i).first][path.get(i).second]
							+ " - ");
		}

		System.out.println("\nLength is: " + (path.size() - 1));
	}
}