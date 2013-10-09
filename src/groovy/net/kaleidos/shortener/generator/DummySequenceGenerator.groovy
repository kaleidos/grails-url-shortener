package net.kaleidos.shortener.generator

import net.kaleidos.shortener.SequenceGenerator
import java.util.concurrent.atomic.AtomicLong

class DummySequenceGenerator implements SequenceGenerator {
    AtomicLong number = new AtomicLong(0)

    Long getNextNumber() {
        return number.incrementAndGet()
    }
}