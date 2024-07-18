package DSA;

public class CustomStack {
    public static void main(String[] args) {
        CustomStacks st=new CustomStacks(5);
        st.push(5);
        st.push(6);
        st.push(8);
        st.push(12);
        int val=st.pop();
        System.out.println(val);
        st.peek();
    }
}

class CustomStacks{
    int[] data;
    int tos;

    CustomStacks(){}

    CustomStacks(int s){
        data=new int[s];
        tos=-1;
    }

    void push(int num){
        if(tos==data.length-1){
            int ndata[]=new int[2*data.length];
            for(int i=0;i<data.length;i++)
                ndata[i]=data[i];
            ndata[tos++]=num;
            data=ndata;

        } else {
            tos++;
            data[tos]=num;
        }
    }

    void peek(){
        if(tos==-1)
            System.out.println("Stack underflow");
        else
            System.out.println(data[tos]);
    }

    int pop(){
        if(tos==-1){
            System.out.print("Stack Underflow");
            return -1;
        }
        else{
            int val=data[tos];
            tos--;
            return val;
        }
    }

    int size(){
        return tos+1;
    }


}
