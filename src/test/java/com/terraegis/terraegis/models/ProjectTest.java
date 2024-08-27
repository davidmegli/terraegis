package com.terraegis.terraegis.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    private Project project;
    private User creator;
    private Category category;

    @BeforeEach
    void setUp() {
        creator = new User();
        creator.setId(1L);
        creator.setName("David Megli");

        category = new Category();
        category.setId(1L);
        category.setName("Technology");

        project = new Project();
        project.setId(1L);
        project.setTitle("Project Title");
        project.setDescription("Project Description");
        project.setImageUrl("http://example.com/image.jpg");
        project.setCreator(creator);
        project.setCategory(category);
        project.setValuation(new BigDecimal("100000.00"));
    }

    @Test
    void getId() {
        assertEquals(1L, project.getId());
    }

    @Test
    void setId() {
        project.setId(2L);
        assertEquals(2L, project.getId());
    }

    @Test
    void getTitle() {
        assertEquals("Project Title", project.getTitle());
    }

    @Test
    void setTitle() {
        project.setTitle("New Title");
        assertEquals("New Title", project.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("Project Description", project.getDescription());
    }

    @Test
    void setDescription() {
        project.setDescription("New Description");
        assertEquals("New Description", project.getDescription());
    }

    @Test
    void getImageUrl() {
        assertEquals("http://example.com/image.jpg", project.getImageUrl());
    }

    @Test
    void setImageUrl() {
        project.setImageUrl("http://example.com/newimage.jpg");
        assertEquals("http://example.com/newimage.jpg", project.getImageUrl());
    }

    @Test
    void getCreator() {
        assertEquals(creator, project.getCreator());
    }

    @Test
    void setCreator() {
        User newCreator = new User();
        newCreator.setId(2L);
        project.setCreator(newCreator);
        assertEquals(newCreator, project.getCreator());
    }

    @Test
    void getCategory() {
        assertEquals(category, project.getCategory());
    }

    @Test
    void setCategory() {
        Category newCategory = new Category();
        newCategory.setId(2L);
        project.setCategory(newCategory);
        assertEquals(newCategory, project.getCategory());
    }

    @Test
    void getValuation() {
        assertEquals(new BigDecimal("100000.00"), project.getValuation());
    }

    @Test
    void setValuation() {
        project.setValuation(new BigDecimal("200000.00"));
        assertEquals(new BigDecimal("200000.00"), project.getValuation());
    }
}
