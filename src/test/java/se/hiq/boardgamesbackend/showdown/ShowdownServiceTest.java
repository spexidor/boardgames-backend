package se.hiq.boardgamesbackend.showdown;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class ShowdownServiceTest {

    @Mock
    private ShowdownRepository showdownRepository;

    @InjectMocks
    private ShowdownService showdownService;

    @Test
    public void getAllShowdownsTest(){
        showdownService.findAll();

        verify(showdownRepository, times(1)).findAll();
    }
}
