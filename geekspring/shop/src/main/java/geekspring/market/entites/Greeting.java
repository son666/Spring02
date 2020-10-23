package geekspring.market.entites;

public class Greeting {

    private String content;
    private int totalOrderItems;

    public Greeting(String content) {
        this.content = content;
    }

    public Greeting(int totalOrderItems) {
        this.totalOrderItems = totalOrderItems;
    }

    public String getContent() {
        return content;
    }

    public int getTotalOrderItems() {
        return totalOrderItems;
    }
}
