import java.util.*;
import java.io.*;

public class Task_B {

	public static class Edge {
		int x;
		int y;
		double weight;
		boolean visited;
		Edge parrent;

		Edge(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		try (BufferedReader fin = new BufferedReader(new FileReader("spantree.in"));
				BufferedWriter fout = new BufferedWriter(new FileWriter("spantree.out"))) {

			int V, E;

			V = Integer.parseInt(fin.readLine());
			E = V * (V - 1) / 2;
			Edge Edgs[] = new Edge[V + 1];

			for (int i = 0; i < V; i++) {
				Edgs[i] = new Edge(0, 0);
				String inp = fin.readLine();
				String[] str = inp.split(" ");
				int Intstr[] = new int[2];
				for (int j = 0; j < 2; j++)
					Intstr[j] = Integer.valueOf(str[j]);
				Edgs[i].x = Intstr[0];
				Edgs[i].y = Intstr[1];
			}

			double distance = 0;
			int pos = 0;
			Edgs[0].visited = true;
			for (int i = 1; i < V; i++)
				Edgs[i].weight = calculateWeight(Edgs[0], Edgs[i]);

			for (int i = 1; i < V - 1; i++) {
				distance = Integer.MAX_VALUE;
				for (int j = 0; j < V; j++) {
					if (!Edgs[j].visited && Edgs[j].weight < distance) {
						pos = j;
						distance = Edgs[pos].weight;
					}
				}

				Edgs[pos].visited = true;
				for (int j = 0; j < V; j++) {
					if (j == pos || Edgs[j].visited)
						continue;

					distance = calculateWeight(Edgs[pos], Edgs[j]);
					if (distance < Edgs[j].weight) {
						Edgs[j].weight = distance;
						Edgs[j].parrent = Edgs[pos];
					}
				}

			}

			double output = 0;
			for (int i = 0; i < V; i++) {
				output += Edgs[i].weight;
			}

			fout.write(String.valueOf(output));
			fin.close();
			fout.close();

		}

		catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static double calculateWeight(Edge a, Edge b) {
		return Math.sqrt(Math.pow(Math.abs(a.x - b.x), 2) + Math.pow(Math.abs(a.y - b.y), 2));

	}

}
