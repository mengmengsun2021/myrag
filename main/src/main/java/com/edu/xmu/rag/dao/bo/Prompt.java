package com.edu.xmu.rag.dao.bo;

import com.edu.xmu.rag.dao.ModelDao;
import com.edu.xmu.rag.dao.UserDao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class Prompt implements Serializable {
    @Builder
    public Prompt(Long id, String name, String code, Integer status, String systemPrompt, String userPrompt, Long modelId, Long userId, LocalDateTime gmtCreate, LocalDateTime gmtModified, ModelDao modelDao, UserDao userDao) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.status = status;
        this.systemPrompt = systemPrompt;
        this.userPrompt = userPrompt;
        this.modelId = modelId;
        this.userId = userId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.modelDao = modelDao;
        this.userDao = userDao;
    }

    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String code;

    @Setter
    @Getter
    private Integer status;

    @Setter
    @Getter
    private String systemPrompt;

    @Setter
    @Getter
    private String userPrompt;

    @Setter
    @Getter
    private Long modelId;

    @Setter
    @ToString.Exclude
    private Model model;

    public Model getModel() {
        if (null == this.modelId) {
            return null;
        }
        if (null == this.model && null != this.modelDao) {
            this.model = modelDao.findModelById(this.modelId);
        }
        return this.model;
    }

    @Setter
    @Getter
    private Long userId;

    @Setter
    @ToString.Exclude
    private User user;

    public User getUser() {
        if (null == this.userId) {
            return null;
        }
        if (null == this.user && null != this.userDao) {
            this.user = userDao.findUserById(this.userId);
        }
        return this.user;
    }

    @Setter
    @Getter
    private LocalDateTime gmtCreate;

    @Setter
    @Getter
    private LocalDateTime gmtModified;

    @Setter
    @JsonIgnore
    @ToString.Exclude
    private ModelDao modelDao;

    @Setter
    @JsonIgnore
    @ToString.Exclude
    private UserDao userDao;
}
