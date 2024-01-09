package com.moutamid.whywhyexpense;

public class Model {
    String name;
    Double price;
    boolean isExpense;

    public Model(String name, Double price, boolean isExpense) {
        this.name = name;
        this.price = price;
        this.isExpense = isExpense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean expense) {
        isExpense = expense;
    }
}
