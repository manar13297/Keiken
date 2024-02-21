import com.keikenpiscine.tdd.Ipv4Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ipv4AddressTest {

    @Test
    void itShouldNotHaveMoreThanFourOneByteBlocks(){
        var validator = new Ipv4Address();
        assertFalse(validator.ValidateIpv4Address("1.1.1.1.1"));
    }

    @Test
    void itShouldNotHaveLessThanFourOneByteBlocks(){
        var validator = new Ipv4Address();
        assertFalse(validator.ValidateIpv4Address("1.1.1"));
    }

    @Test
    void itShouldNotIncludeLetters(){
        var validator = new Ipv4Address();
        assertFalse(validator.ValidateIpv4Address("198.b.c.d"));
    }

    @Test
    void itShouldHaveBytesWithinRangeZeroAnd255(){
        var validator = new Ipv4Address();
        assertFalse(validator.ValidateIpv4Address("256.255.-1.1"));
    }

    @Test
    void itShouldNotEndWith255(){
        var validator = new Ipv4Address();
        assertFalse(validator.ValidateIpv4Address("255.255.255.255"));
    }

    @Test
    void itShouldNotEndWith0(){
        var validator = new Ipv4Address();
        assertFalse(validator.ValidateIpv4Address("1.1.1.0"));
    }



    @Test
    @DisplayName("Should have an Ipv4 @ format")
    void itShouldBeGroupedIntoFourOneByteBlocksSeperatedByDottedDecimalNotation(){
        var validator = new Ipv4Address();
        assertTrue(validator.ValidateIpv4Address("192.168.1.1"));
    }


}
