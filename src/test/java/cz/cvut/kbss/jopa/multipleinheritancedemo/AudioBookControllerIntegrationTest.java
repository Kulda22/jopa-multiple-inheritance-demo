package cz.cvut.kbss.jopa.multipleinheritancedemo;

import com.fasterxml.jackson.core.type.TypeReference;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.AudioBook;
import cz.cvut.kbss.jopa.multipleinheritancedemo.util.InstanceGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AudioBookControllerIntegrationTest extends TestBase {
    InstanceGenerator instanceGenerator = new InstanceGenerator();

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        super.setUp();
    }

    @Test
    void shouldReturnDefaultMessage() throws Exception {
//        this.mockMvc.perform(get("/audio-books")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("title")));


        final MvcResult mvcResult = mockMvc.perform(get("/audio-books").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        final List<AudioBook> result = readValue(mvcResult, new TypeReference<List<AudioBook>>() {
        });

        for (AudioBook a : result) {
            System.out.println(a);
        }
//        assertEquals(result.size(), 5);
    }

    @Test
    void persistedEntityCanBeFoundViaFindAll() throws Exception {
        AudioBook newAudioBook = instanceGenerator.generateAudioBook();
        newAudioBook.generateUri();
        mockMvc.perform(post("/audio-books").content(toJson(newAudioBook)).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated()).andReturn();


        final MvcResult mvcResult = mockMvc.perform(get("/audio-books").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        final List<AudioBook> result = readValue(mvcResult, new TypeReference<List<AudioBook>>() {
        });

        assertTrue(result.contains(newAudioBook));
    }

    @Test
    void persistedEntityCanBeFoundByISNB() throws Exception {
        AudioBook newAudioBook = instanceGenerator.generateAudioBook();
        newAudioBook.generateUri();
        mockMvc.perform(post("/audio-books").content(toJson(newAudioBook)).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated()).andReturn();


        final MvcResult mvcResult = mockMvc.perform(get("/audio-books/{isnb}",newAudioBook.getISNB()).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        final AudioBook result = readValue(mvcResult, new TypeReference<AudioBook>() {
        });

        assertEquals(result, newAudioBook);
    }
}
