package com.unuse.tetris.api;

/**
 * Created by Unuse on 2017/8/2.
 */
public class Tetris {

    private Integer from;

    private Integer to;

    private Integer type;  // 生成的方块类型

    private Integer action;  // 方块变化动作 1-左 2-右 3-旋转

    private Integer role;  // 用户角色 (1-左右 2-变换)

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
