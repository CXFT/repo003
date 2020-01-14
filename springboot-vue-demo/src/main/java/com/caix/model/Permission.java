package com.caix.model;

import java.util.List;
import java.util.Objects;

public class Permission {

    private Integer id;
    private String name;
    private String url;
    private Permission parentPermission;
    private List<Permission> childPermission;

    public Permission() {
    }

    public Permission(String name, String url, Permission parentPermission, List<Permission> childPermission) {
        this.name = name;
        this.url = url;
        this.parentPermission = parentPermission;
        this.childPermission = childPermission;
    }

    public Permission(String name, String url, Permission parentPermission) {
        this.name = name;
        this.url = url;
        this.parentPermission = parentPermission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Permission getParentPermission() {
        return parentPermission;
    }

    public void setParentPermission(Permission parentPermission) {
        this.parentPermission = parentPermission;
    }

    public List<Permission> getChildPermission() {
        return childPermission;
    }

    public void setChildPermission(List<Permission> childPermission) {
        this.childPermission = childPermission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", parentPermission=" + parentPermission +
                ", childPermission=" + childPermission +
                '}';
    }
}
