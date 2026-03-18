public class Decompresser{
    private Data getFrecuency(String txt){

    }

    public String DecompressGivenFrecuency(Data data, String binarytxt){
        String currenttxt=binarytxt;
        String element="";

        for(int i=0, i<data.lenght, i++)
        {
            for(int j, j<currentxt.lenght, j++)
            {
                String c =data.get(currenttxt.slice(0,j));
                if(c==null)
                {
                    continue;
                }
                else
                {
                    element+=ascci(c)
                    currenttxt= currenttxt.slice(j);
                }
            }
        }
    }

    }

    public String decompress(String txt){
        Data data=getFrecuency(txt);
        String binarytxt = txt.slice(data.lenght);

        return DecompressGivenFrecuency(data, binarytxt);

    }
}

class Data{
    private int length
    private Map<String,Integer> frecuency_table


    public String get(String key){
        return frecuency_table.get(key);
    }

}