import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class sdfgvklsdfvjnsdfvjn {
    private static int[] arr = {-1,0,1,2,-2,-4,3,4,7,-8};
    private static int[] arr1 = {-1,0,1,10,15,17,21,23,24,25,26,27};
    private static String[] arr2 = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","p","p","p","p","i"};

    public static void main(String[] args) throws UnknownHostException {
        Map<String, Integer> dataMap = new HashMap<>();

        for(String key : arr2){
            dataMap.put(key, dataMap.getOrDefault(key, 0) + 1);
        }
        StringBuffer sb = new StringBuffer();
        dataMap.keySet().iterator().forEachRemaining(o -> {
            int maxCnt = dataMap.getOrDefault(o.toString(), 0);
            for( int i=0; i<maxCnt / 2; i++ ){
                sb.append(o);
            }
        });

        System.out.println(sb.toString() + "" + sb.reverse().toString());


        /*
        int searchData = 21;
        int minIdx = 0;
        int maxIdx = arr1.length;
        int idx = -1;

        do{
            idx = Math.floorDiv(minIdx + maxIdx, 2);
            if( minIdx == idx || maxIdx == idx ){
                idx = -1;
                break;
            }
            else if( arr1[idx] > searchData ){
                maxIdx = idx;
            }
            else if( arr1[idx] < searchData ){
                minIdx = idx;
            }
        }while( searchData != arr1[idx] );

        System.out.println("searchData idx :: " + idx);
        */

    }
}
