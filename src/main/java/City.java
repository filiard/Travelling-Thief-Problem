import java.util.ArrayList;

public class City {

    int id;
    int x;
    int y;
    ArrayList<Item> items = new ArrayList<>();

    public City(int id, int x, int y){
        this.id=id;
        this.x = x;
        this.y = y;
    }

    public int getId() { return this.id; }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setItem(Item item){
        items.add(item);
    }

    public Item findBestRatioItem(){
        Item bestItem = null;
        float bestItemRatio=0;

        for (Item item:items)
        {
            if(bestItem==null)
            {
                bestItem=item;
            }
            if(item.getRatio()>=bestItem.getRatio()){
                bestItem=item;
            }
        }
        return bestItem;
    }

    public Item findLightestItem(){
        Item bestItem = null;
        //float bestItemRatio=bestItem.profit/bestItem.weigth;
        for (Item item:items)
        {
            if(bestItem==null){
                bestItem=item;
            }
            if (item.weigth<bestItem.weigth){
                bestItem=item;
            }
        }
        return bestItem;
    }

    public Item findMostValuableItem(){
        Item bestItem = null;
        //float bestItemRatio=bestItem.profit/bestItem.weigth;
        for (Item item:items)
        {
            if(bestItem==null){
                bestItem=item;
            }
            if (item.profit>bestItem.profit){
                bestItem=item;
            }
        }
        return bestItem;
    }


    public double distanceTo(City city){
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        return distance;
    }


    @Override
    public String toString(){
        return getId()+", "+getX()+", "+getY();
    }
}