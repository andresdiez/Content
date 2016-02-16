package com.example.adiez.content;



import org.junit.Test;
import java.util.Arrays;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ModelTest {
    @Test
    public void addMessageValidator(){


        Model m= mock(Model.class);
        when(m.getMessages()).thenReturn(Arrays.asList(new Message(1, "asd", "asdas")));
        m.getMessages();
        m.loadMessages();
        System.out.print(m.getMessages().size());






    }

}
