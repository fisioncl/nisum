package ui;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.AdditionalAnswers.returnsSecondArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nisum.myinventory.exception.UIException;
import com.nisum.myinventory.ui.ItemMenu;
import com.nisum.myinventory.vo.Item;

public class ItemMenuTest {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDD");
    @Mock
    ItemMenu imenu;

    @Before
    public void before() throws UIException{
        MockitoAnnotations.initMocks(this);

        doThrow(new UIException("Item does not exists.")).when(imenu).showItem(eq(0L));
        doThrow(new UIException("Item does not exists.")).when(imenu).updateItem(eq(0L), any(Item.class));
        doThrow(new UIException("Item does not exists.")).when(imenu).deleteItem(eq(0L));

        //when(imenu.updateItem(anyLong(), (Item)notNull())).thenAnswer(returnsSecondArg());
        when(imenu.createItem(any(Item.class))).then(returnsFirstArg());
    }

    @Test(expected = UIException.class)
    public void testMenuCreateException() throws UIException {
        Item it = new Item(0L, "", new Date());
        imenu.createItem(it);
    }

    @Test(expected = UIException.class)
    public void testMenuDeleteException() throws UIException {
        imenu.deleteItem(0L);
    }

    @Test(expected = UIException.class)
    public void testMenuUpdateException() throws UIException {
        imenu.updateItem(0L, new Item());
    }

    @Test(expected = UIException.class)
    public void testMenuShowException() throws UIException {
        imenu.showItem(0L);
    }

    @Test
    public void testMenuCreation() throws UIException, ParseException {
        Item i = new Item(123L, "Hello Stg.", sdf.parse("20150101"));

        //Objects should be the same.
        assertEquals(imenu.createItem(i), i);
    }
}