//implement best-first robot search

#include <stdio.h>
#include <string>
#include <iostream>
#include <fstream>
#include <tuple>

using namespace std;

vector< vector<char> > parseGrid(string fname){
	// Parse input file
	ifstream ifs(fname);
	string content( (std::istreambuf_iterator<char>(ifs) ),
                  (std::istreambuf_iterator<char>()  ) );
	int n = content[0];
	vector< vector<char> > result;
	for(int i = 0; i < n; i++){
		for(int j = 0; j < n; j++){
			result[i][j] = content[i*n+j+1];
		}
	}
}

int main(int argc, char *argv[]){
	string filename;
	// Get filename from command line inputs

	if(argc < 2){cout << "No file specified in command line\n";}
	else{filename = argv[1];}
	if(argc > 2){cout << "Extra command line input detected";}

	// Parse map and store relevant values
	tuple<int,int> initialState;
	tuple<int,int> goalState;
	vector< tuple<int,int> > obstacles;
	vector< vector<char> > grid;
	int N;
	// Get dimensions
	ifstream infile(filename);
	string line;
	getline(infile, line);
	N = stoi(line);

	grid = parseGrid(filename);
	for(int i = 0; i < N; i++){
		for(int j = 0; j < N; j++){
				cout << grid[i][j];
		}
		cout << "\n";
	}
	// Traverse grid and set variables
	for(int i = 0; i < N; i++){
		for(int j = 0; j < N; j++){
				if(grid[i][j] == 'i'){initialState = (i,j);}
				if(grid[i][j] == 'g'){goalState = (i,j);}
				if(grid[i][j] == '+'){obstacles.append((i,j));}
		}
	}

}
