import java.util.LinkedList;

public class MachineOfPost {

    private final LinkedList<Integer> tapeElements=new LinkedList<>();
    private int index=0;

    public void splittingTape(String str) {
        String[] element = str.split(" ");
        for (String el : element) {
            tapeElements.add(Integer.parseInt(el)) ;
        }
    }
    public void increaseIndex(){
        index++;
        if (index>tapeElements.size())
            tapeElements.add(index-1,0);

    }
    public void decreaseIndex(){
        index--;
        if (index<0)
        {
            tapeElements.add(0,0);
            index=0;
        }
    }
    public String condition(String str)
    {
        String [] secondElement= str.split(",");
        if (tapeElements.get(index)==0)
        return secondElement[0];
        else return secondElement[1];
    }

    public void chargeValue(int value)
    {
        tapeElements.set(index,value);
    }

    public void splittingHead(String str) {
        String[] element = str.split(" ");
        for (String el : element) {
            index++;
            if (el.equals("|")) {
                index/=2;
            }
        }
    }

    public  void getHeadIndex()
    {
        System.out.println(index);
    }

    public void getTape()
    {
        for (Integer el:tapeElements)
            System.out.print(el+" ");
    }
    public int getTapeValue(int index)
    {
       return tapeElements.get(index);
    }

    public int getIndex() {
        return index;
    }
}