package com.example.diffut;

/**
 * 因为使用DiffUtils比较新老数据集差异时，会遍历新老数据集的每个data，要确保他们的内存地址（指针）不一样，
 * 否则比较的是新老data是同一个，就一定相同，要确保内存地址不同，而不是相同对象的属性不同
 */
public class TestBean implements Cloneable {
    private int itemID;
    private String name;
    private String desc;
    private String textAnim;

    /**
     *
     * @param id   记录每个Item的id
     * @param name item的名字
     * @param desc item的描述信息
     * @param animText item的动画
     */
    public TestBean(int id, String name, String desc, String animText) {
        this.itemID = id;
        this.name = name;
        this.desc = desc;
        this.textAnim = animText;
    }

    public void setItemID(int Id) {
        this.itemID = Id;
    }

    public int getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTextAnim() {return textAnim;}

    public void setTextAnim(String textAnim) {
        this.textAnim = textAnim;
    }

    //实现将对象赋值到不同内存地址中，模拟数据刷新
    @Override
    public TestBean clone() throws CloneNotSupportedException {
        TestBean bean = null;
        try {
            bean = (TestBean) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return bean;
    }
}