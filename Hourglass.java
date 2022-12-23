public class Hourglass {

    static class OutputParams{
        int[][] result;
    }
    static class InputParams{
        int[][] arr;
        boolean cord;
        boolean printMatrix;

        int ri;
        int ci;
        int max;
    }


    public static OutputParams findSums(InputParams inp){
        OutputParams finalOutput = new OutputParams();

        int max = inp.arr[0][0];
        if (inp.printMatrix){
            int ri=inp.ri;
            int ci=inp.ci;
            int[][] output = new int[3][3];
            int i=0,j;
            for(int r=ri;r<inp.arr.length/2+ri;r++) {
                j=0;
                for (int c = ci; c < inp.arr.length / 2 + ci; c++) {
                    output[i][j]=inp.arr[r][c];
                    j++;
                }
                i++;
            }
            finalOutput.result = output;
            return finalOutput;
        }
        for(int ri=0;ri<inp.arr.length-2;ri++){
            for(int ci=0;ci<inp.arr.length-2;ci++){
                int sum=0;
                int count=0;
                for(int r=ri;r<inp.arr.length/2+ri;r++){
                    for(int c=ci;c<inp.arr.length/2+ci;c++){
                        count++;
                        if (count==4 || count==6){
                            continue;
                        }
                        sum = sum+inp.arr[r][c];
                    }
                }
                if (inp.cord) {
                    if (inp.max==sum){
                        inp.ci = ci;
                        inp.ri = ri;
                        inp.cord=false;
                        inp.printMatrix=true;
                        return findSums(inp);
                    }
                }
                if(sum>max){
                    max=sum;
                }
            }

        }
        inp.max = max;
        inp.cord = true;
        return findSums(inp);
    }



    public static void main(String[] args){
        int[][] arr = {
                {1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 0, 2, 4, 4, 0},
                {0, 0, 0, 2, 0, 0},
                {0, 0, 1, 2, 4, 0},
        };
        InputParams inp = new InputParams();
        inp.arr = arr;
        inp.cord = false;
        inp.printMatrix = false;
        OutputParams out = new OutputParams();
        out = findSums(inp);
        for(int r=0;r<out.result.length;r++) {
            for (int c = 0; c < out.result.length; c++) {
                System.out.print(out.result[r][c] + "  ");
            }
            System.out.println();
        }
    }
}
