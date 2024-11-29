package by.kurilo.machineofpost;

import java.util.LinkedList;

public class MachineOfPost {

    private final LinkedList<Integer> tapeElements=new LinkedList<>();
    private int carriageIndex =0;

    public void splittingTape(String tape) {
        String[] elementOfTape = tape.split(" ");
        for (String element : elementOfTape) {
            tapeElements.add(Integer.parseInt(element)) ;
        }
    }
    public void increaseIndex(){
        carriageIndex++;
        if (carriageIndex >tapeElements.size())
            tapeElements.add(carriageIndex -1,0);

    }
    public void decreaseIndex(){
        carriageIndex--;
        if (carriageIndex <0)
        {
            tapeElements.add(0,0);
            carriageIndex =0;
        }
    }
    public String condition(String secondElement)
    {
        String [] partSecondElement= secondElement.split(",");
        if (tapeElements.get(carriageIndex)==0)
        return partSecondElement[0];
        else return partSecondElement[1];
    }

    public void chargeCarriageValue(int value)
    {
        tapeElements.set(carriageIndex,value);
    }

    public void splittingHead(String firstFileLine) {
        String[] element = firstFileLine.split(" ");
        for (String el : element) {
            carriageIndex++;
            if (el.equals("|")) {
                carriageIndex /=2;
            }
        }
    }

    public  void getHeadIndex()
    {
        System.out.println(carriageIndex);
    }

    public void getTape()
    {
        for (Integer tapeElement :tapeElements)
            System.out.print(tapeElement +" ");
    }
    public int getTapeValue(int index)
    {
       return tapeElements.get(index);
    }

    public int getCarriageIndex() {
        return carriageIndex;
    }
}