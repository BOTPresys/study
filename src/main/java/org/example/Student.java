package org.example;
import java.util.HashSet;
import java.util.Objects;
//HashSet 去重（坑点体验）：
//定义一个简单的 Student 类，只有 name 和 age 属性（生成构造器、getter/setter）。
//在 main 中新建两个 Student 对象：s1("张三",18) 和 s2("张三",18)。
//把它们都加到 HashSet<Student> 里。
//打印 set.size()，看看是 1 还是 2？如果是 2，说明没有去重成功。
//进阶：在 Student 类里按下 Alt+Insert（IDEA快捷键），选择 equals() and hashCode()，让 IDEA 帮你自动生成这两个方法。再次运行，看看 size() 是不是变成 1 了。

public class Student {
    protected String name;
    protected int age;
    public Student(){}
    public Student(String name,int age){
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
