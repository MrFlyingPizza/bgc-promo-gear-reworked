package ca.bgcengineering.promogearreworked;

import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryCreate;
import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class SecuredCategoryTests {

    private static final String CATEGORY_PATH = "/api/secured/categories";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCategoryCreate() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String name = "random category";
        SecuredCategoryCreate request = new SecuredCategoryCreate(name, null);
        MockHttpServletResponse response = mockMvc.perform(post(CATEGORY_PATH).content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        String json = response.getContentAsString();
        SecuredCategoryResponse result = mapper.readValue(json, SecuredCategoryResponse.class);
        assertEquals(result.getName(), name);
        assertNull(result.getParent());
    }

    @Test
    void testCategoryParentRefresh() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String parentName = "test parent";
        SecuredCategoryCreate request = new SecuredCategoryCreate(parentName, null);
        MockHttpServletResponse response = mockMvc.perform(post(CATEGORY_PATH).content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        SecuredCategoryResponse result = mapper.readValue(response.getContentAsString(), SecuredCategoryResponse.class);

        String childName = "test child";
        request = new SecuredCategoryCreate(childName, result.getId());
        response = mockMvc.perform(post(CATEGORY_PATH).content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        SecuredCategoryResponse childResult = mapper.readValue(response.getContentAsString(), SecuredCategoryResponse.class);

        assertNotNull(childResult.getParent());
        assertEquals(childResult.getParent().getId(), result.getId());
        assertEquals(childResult.getParent().getName(), parentName);
    }

}
