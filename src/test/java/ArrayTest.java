import DSA.Array;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ArrayTest {

    @Test
    public void Test1(){
        Array arr=new Array();
        int[] result=arr.diffOf2Arrays();
        StringBuilder res=new StringBuilder();
        Arrays.stream(result).forEach(i -> res.append(i));
        StringBuilder exp=new StringBuilder("849");
        Assert.assertEquals(exp.toString(),res.toString());
    }
}
