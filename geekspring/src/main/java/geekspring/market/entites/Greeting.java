package geekspring.market.entites;

public class Greeting {

    private String content;
    private int totalOrderItems;

    public Greeting(String content, int totalOrderItems) {
        this.content = content;
        this.totalOrderItems = totalOrderItems;
    }

    public String getContent() {
        return content;
    }

    public int getTotalOrderItems() {
        return totalOrderItems;
    }
}
