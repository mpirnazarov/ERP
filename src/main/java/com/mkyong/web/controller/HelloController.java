package com.mkyong.web.controller;

public class HelloController {

    public static void main(String[] args) {
        Tree<Integer, String> tree = new Tree<>(1, "Bob");

        tree.addChild(1, 2, "John");
        tree.addChild(1, 3, "James");
        tree.addChild(2, 4, "David");
        tree.addChild(2, 5, "Alice");
        tree.addChild(2, 5, "Alice");

        System.out.println(tree.subtreeToString(1));
        System.out.println(tree.subtreeToString(1));
    }
}