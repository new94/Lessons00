package ru.enenakhov.mai.lessons.lesson08;

public class ClassA<T> {

    private Processing<T> function;

    public void process(T... message){
        function.process(message);
    }

    public Processing<T> getFunction() {
        return function;
    }

    public void setFunction(Processing<T> function) {
        this.function = function;
    }
}
