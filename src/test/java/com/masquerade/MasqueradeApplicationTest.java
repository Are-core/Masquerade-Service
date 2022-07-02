package com.masquerade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MasqueradeApplicationTest {

    @Test
    void main() {
        try {
            MasqueradeApplication.main(new String[]{});
        } catch (Exception e) {
            fail();
        }
    }
}