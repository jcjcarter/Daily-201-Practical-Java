package com.company;

import java.util.ArrayList;

public class Main {


    static enum option{A, B};



    public static void main(String[] args){
        PriorityQueue q = new PriorityQueue();
        //Testing can be done here
        for(int i = 0; i < 10; i++){
            q.enqueue((char)(i+60) + "0", (int)(Math.random()*50), (int)(Math.random()*50), i);
        }
    }

    static class PriorityQueue{

        private static ArrayList<PriorityNode> list;


        public PriorityQueue(){
            list = new ArrayList<PriorityNode>();
            System.out.println("Created new PriorityQueue");
        }

        public void enqueue(String str, int a, int b, int addOrder){
            PriorityNode n = new PriorityNode(str, a, b, addOrder);
            list.add(n);
        }

        public String dequeueA(){
            return dequeue(option.A);
        }

        public String dequeueB(){
            return dequeue(option.B);
        }

        public String dequeue(option o){
            ArrayList<PriorityNode> tempList = new ArrayList<PriorityNode>();
            PriorityNode highest = null;
            for(PriorityNode n : list){
                if(highest == null){
                    highest = n;
                } else{
                    if(o == option.A ? (n.getPriorityA() > highest.getPriorityA()):(n.getPriorityB() > highest.getPriorityB())){
                        highest = n;
                        tempList.clear();
                        tempList.add(n);
                    } else if(o == option.A ? (n.getPriorityA() == highest.getPriorityA()):(n.getPriorityB() == highest.getPriorityB())){
                        tempList.add(n);
                    }
                }
            }

            if(tempList.size() == 1){
                return tempList.get(0).getData();
            } else{
                int bestOrder = -1;
                highest = null;
                for(PriorityNode n : tempList){
                    if(n.getAddOrder() > bestOrder){
                        bestOrder = n.getAddOrder();
                        highest = n;
                    }
                }
                return highest.getData();
            }
        }

        public int count(){
            return list.size();
        }

        public void clear(){
            list.clear();
        }
    }

    private class PriorityNode{

        private String data;
        private int priorityA;
        private int priorityB;
        private int addOrder;

        public PriorityNode(String str, int a, int b, int addOrder){
            this.data = str;
            this.priorityA = a;
            this.priorityB = b;
            this.addOrder = addOrder;
        }

        public String getData() {
            return data;
        }

        public int getPriorityA() {
            return priorityA;
        }

        public int getPriorityB() {
            return priorityB;
        }

        public int getAddOrder() {
            return addOrder;
        }
    }
}
