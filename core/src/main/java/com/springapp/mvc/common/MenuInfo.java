package com.springapp.mvc.common;

/**
 * Меню в шапке сайта
 */
public class MenuInfo {

    /**
     * id пункта меню
     */
    private Long id;

    /**
     * Название пункта меню
     */
    private String name;

    /**
     * Ссылка на соответствующий раздел
     */
    private String link;


    public MenuInfo() {
    }

    public MenuInfo(Long id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
