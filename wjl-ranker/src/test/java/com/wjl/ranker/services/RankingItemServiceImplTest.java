package com.wjl.ranker.services;

import com.wjl.ranker.Constants;
import com.wjl.ranker.entities.Category;
import com.wjl.ranker.entities.RankingItem;
import com.wjl.ranker.exceptions.GeneralException;
import com.wjl.ranker.repositories.RankingItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class RankingItemServiceImplTest {

    public static final String ENTITY = "Item";
    Category novel;
    Category movie;
    RankingItem alpha;
    RankingItem beta;
    RankingItem charlie;
    @Spy
    private RankingItemRepo rankingItemRepo;
    @InjectMocks
    private RankingItemServiceImpl rankingItemService;

    @BeforeEach
    public void setUp() {
        alpha = new RankingItem(
                "alpha",
                novel);
        beta = new RankingItem(
                "beta",
                movie);
        charlie = new RankingItem(
                "charlie",
                null);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        List<RankingItem> rankingItem = Arrays.asList(alpha, beta, charlie);
        when(rankingItemRepo.findAll()).thenReturn(rankingItem);

        List<RankingItem> result = rankingItemService.getAllRankingItems();

        verify(rankingItemRepo).findAll();
        assertNotNull(result);
        assertEquals(rankingItem.size(), result.size());
    }

    @Test
    void testGetCategoryById() {
        long id = 1L;
        RankingItem rankingItem = alpha;
        rankingItem.setId(id);
        when(rankingItemRepo.findById(id)).thenReturn(Optional.of(rankingItem));

        RankingItem result = rankingItemService.getRankingItemById(id);

        assertNotNull(result);
        assertEquals(id, result.getId().longValue());
    }

    @Test
    void testCreateCategory() {
        when(rankingItemRepo.save(alpha)).thenReturn(alpha);

        RankingItem result = rankingItemService.createRankingItem(alpha);

        assertNotNull(result);
        assertEquals(alpha.getName(), result.getName());
        verify(rankingItemRepo, times(1)).save(alpha);
    }

    @Test
    void testUpdateCategory() {
        long id = 1L;
        RankingItem existingItem = alpha;
        existingItem.setId(id);
        RankingItem updatedItem = beta;
        updatedItem.setId(id);

        when(rankingItemRepo.findById(id)).thenReturn(Optional.of(existingItem));
        when(rankingItemRepo.save(existingItem)).thenReturn(existingItem);

        RankingItem result = rankingItemService.updateRankingItem(updatedItem);

        assertNotNull(result);
        assertEquals(alpha.getName(), result.getName());
    }

    @Test
    void testDeleteCategory() {
        long id = 1L;
        when(rankingItemRepo.existsById(id)).thenReturn(true);

        rankingItemService.deleteRankingItem(id);

        verify(rankingItemRepo, times(1)).deleteById(id);
    }

    @Test
    void testDeleteCategory_NotFound() {
        long id = 1L;
        when(rankingItemRepo.existsById(id)).thenReturn(false);

        try {
            rankingItemService.deleteRankingItem(id);
        } catch (GeneralException e) {
            assertEquals("Error: " + String.format(Constants.EXCEPTION_GENERAL_FAILED_DELETE, ENTITY), e.getMessage());
        }
    }
}
