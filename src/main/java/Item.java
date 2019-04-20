public class Item {
    int id, profit, weigth, nodeNumber;

    public Item(int id, int profit, int weight, int nodeNumber) {
        this.id=id;
        this.profit = profit;
        this.weigth = weight;
        this.nodeNumber = nodeNumber;
    }

    public Item(int id, int profit, int weight) {
        this.id=id;
        this.profit = profit;
        this.weigth = weight;

    }

    public int getId() {
        return id;
    }

    public int getProfit() {
        return profit;
    }

    public int getWeigth() {
        return weigth;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public double getRatio(){
        return (double)profit/weigth;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                "profit=" + profit +
                ", weight=" + weigth +
                ", nodeNumber=" + nodeNumber +
                '}';
    }
}
