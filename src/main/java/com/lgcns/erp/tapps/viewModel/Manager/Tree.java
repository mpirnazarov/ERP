package com.lgcns.erp.tapps.viewModel.Manager;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.viewModel.PersonInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Muslimbek on 12/19/2016.
 */
public class Tree<I, A> {
    private final HashMap<I, Node<I, A>> map = new HashMap<>();
    private final Node<I, A> root;
    public Tree(I id, A value) {
        root = new Node<>(id, value);
        map.put(id, root);
    }

    public void addChild(I parentId, I id, A value) {
        Node<I, A> parent = map.get(parentId);
        Node<I, A> child = new Node<>(id, value);
        parent.children.add(child);
        map.put(id, child);
    }

    public A getById(I id) {
        return map.get(id).value;
    }

    public String subtreeToString(I id) {
        return map.get(id).toString();
    }

    public List<PersonInfo> subTree(I i) {
        return map.get(i).subTree();
    }

    private static class Node<I, A> {
        private final I id;
        private final A value;
        private final ArrayList<Node<I, A>> children = new ArrayList<>();
        private List<PersonInfo> childrens = new LinkedList<>();
        private PersonInfo personInfo = null;

        private Node(I id, A value) {
            this.id = id;
            this.value = value;
        }

        public List<PersonInfo> subTree() {
            createSubTree(0, childrens);
            return childrens;
        }

        private void createSubTree(int depth, List<PersonInfo> childrens) {
            UserLocalizationsEntity userLoc = UserService.getUserLocByUserId(Integer.parseInt(id.toString()), 3);
            UsersEntity user = UserService.getUserById(Integer.parseInt(id.toString()));
            personInfo = new PersonInfo(userLoc.getFirstName(), userLoc.getLastName(), userLoc.getUserId());
            personInfo.setId(String.format("%05d", user.getId()));
            personInfo.setUserName(user.getUserName());
            personInfo.setRoleId(user.getRoleId());
            childrens.add(personInfo);
            for (Node<I, A> child : children) {
                child.createSubTree(depth + 1, childrens);
            }
        }
    }
}
