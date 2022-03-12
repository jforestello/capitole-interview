package org.jforestello.mytheresa_interview.infrastructure.model

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class InMemoryStorage<D, E: Record<D>>(
    internal val storage: ConcurrentMap<RecordID, E> = ConcurrentHashMap(),
    private val lock: Mutex = Mutex(),
) {

    internal abstract fun getRecord(domain: D, id: RecordID): E

    suspend fun create(domain: D) {
        val index = storage.size
        val element = getRecord(domain, index)
        lock.withLock {
            storage[element.id] = element
        }
    }

    fun get(id: RecordID): E? {
        return storage[id]
    }

    suspend fun update(id: RecordID, domain: D) {
        val element = getRecord(domain, id)
        if (storage[element.id] == null) {
            return
        }
        lock.withLock {
            storage[element.id] = element
        }
    }

    suspend fun delete(id: RecordID) {
        if (storage[id] == null) {
            return
        }
        lock.withLock {
            storage.remove(id)
        }
    }
}
