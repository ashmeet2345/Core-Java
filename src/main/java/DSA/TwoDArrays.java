package DSA;

import java.util.Arrays;

public class TwoDArrays {
    public static void matrixMultiplication(int[][] arr1,int[][] arr2){
        int[][] arr3;
        if(arr1[0].length != arr2.length){
            System.out.println("Invalid matrices");
            return;
        } else {
            arr3=new int[arr1.length][arr2[0].length];
            for(int i=0;i<arr3.length;i++){
                for(int j=0;j<arr3[0].length;j++){
                    for(int k=0;k<arr1[0].length;k++){
                        arr3[i][j]+=arr1[i][k]*arr2[k][j];
                    }
                }
            }

            for(int i=0;i<arr3.length;i++){
                for(int j=0;j<arr3[0].length;j++){
                    System.out.print(arr3[i][j]+" ");
                }
                System.out.println();
            }
        }
    }

    public static void wavyTraversal(int[][] arr){
        for(int j=0;j<arr[0].length;j++){
            if(j%2==0){
                for(int i=0;i<arr.length;i++){
                    System.out.print(arr[i][j]+" ");
                }
            } else {
                for(int i=arr.length-1;i>=0;i--){
                    System.out.print(arr[i][j]+" ");
                }
            }
        }
    }

    public static void spiralTraversal(int[][] arr){
        int minr=0;
        int minc=0;
        int mxr=arr.length-1;
        int mxc=arr[0].length-1;
        int tn=arr.length*arr[0].length;
        int cnt=0;
        while(cnt<tn){
            for(int i=minr;i<=mxr && cnt<tn;i++){
                System.out.print(arr[i][minc]+" ");
                cnt++;
            }
            minc++;
            for(int i=minc;i<=mxc && cnt<tn;i++){
                System.out.print(arr[mxr][i]+" ");
                cnt++;
            }
            mxr--;
            for(int i=mxr;i>=minr && cnt<tn;i--){
                System.out.print(arr[i][mxc]+" ");
                cnt++;
            }
            mxc--;
            for(int i=mxc;i>=minc && cnt<tn;i--){
                System.out.print(arr[minr][i]+" ");
                cnt++;
            }
            minr++;
        }
    }

    public static void exitPoint(int[][] arr){
        int i=0;
        int j=0;
        int dir=0;
        while(true){
            dir=(dir+arr[i][j])%4;
            if(dir == 0){
                j++;
            } else if(dir == 1){
                i++;
            } else if(dir == 2){
                j--;
            } else if(dir == 3){
                i--;
            }

            if(i < 0){
                i++;
                break;
            }else if(j < 0){
                j++;
                break;
            }else if(i == arr.length){
                i--;
                break;
            }else if(j == arr[0].length){
                j--;
                break;
            }
        }
        System.out.println(i+","+j);
    }

    public static void rotateBy90(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=i;j<arr[0].length;j++){
                int temp=arr[i][j];
                arr[i][j]=arr[j][i];
                arr[j][i]=temp;
            }
        }


        int k=arr[0].length-1;
        for(int j=0;j<=k;j++){
            for(int i=0;i<arr.length;i++){
                int temp=arr[i][j];
                arr[i][j]=arr[i][k];
                arr[i][k]=temp;
            }
            k--;
        }
        display(arr);
    }

    public static void display(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void diagonalTraversal(int[][] arr){
        for(int g=0;g<arr.length;g++){
            for(int i=0,j=g;j<arr.length;i++,j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] arr1={{5,5},{3,3}};
        int[][] arr2={{2,6},{7,4}};
        matrixMultiplication(arr1,arr2);

        int[][] arr={{1,2,3,4},
                     {5,6,7,8},
                     {9,10,11,12},
                     {13,14,15,16}};
        wavyTraversal(arr);
        System.out.println();
        spiralTraversal(arr);
        System.out.println();

        int[][] a={{0,0,0,1},{1,0,0,0},{1,0,0,1}};
        exitPoint(a);

        System.out.println();
        rotateBy90(arr);

        System.out.println();
        diagonalTraversal(arr);

        System.out.println();
        Pair[] pair=new Pair[5];
        pair[0]=new Pair(2,3);
        pair[1]=new Pair(3,8);
        pair[2]=new Pair(6,1);
        pair[3]=new Pair(8,4);
        pair[4]=new Pair(9,6);

        Arrays.sort(pair);

        Arrays.stream(pair).forEach(i -> System.out.print(i.a+","+i.b+" "));
    }

    public static class Pair implements Comparable<Pair> {
        int a;
        int b;
        public Pair(int a,int b){
            this.a=a;
            this.b=b;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }

        @Override
        public int compareTo(Pair o) {
            int diff=(int)(this.b-o.b);
            return diff;
        }
    }
}
