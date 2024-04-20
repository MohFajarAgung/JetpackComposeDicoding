package com.dicoding.listmusik

import com.dicoding.listmusik.model.Musik
import com.dicoding.listmusik.model.MusikData
import com.dicoding.listmusik.model.MusikList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MusikRepository {

    private val musikList = mutableListOf<MusikList>()

    init {
        if (musikList.isEmpty()) {
            MusikData.musik.forEach {
                musikList.add(MusikList(it, 0))
            }
        }
    }

   private fun getMusik(): Flow<List<MusikList>> {
        return flowOf(musikList)
    }

     fun getAllMusik(): List<Musik> {
        return MusikData.musik
    }

    fun searchMusik(query: String): List<Musik> {
        return MusikData.musik.filter {
            it.judul.contains(query, ignoreCase = true)
        }

    }

    fun getMusikById(musikId: Long): MusikList {
        return musikList.first {
            it.musik.id == musikId
        }
    }

    fun updateMusik(rewardId: Long, newCountValue: Int): Flow<Boolean> {
        val index = musikList.indexOfFirst { it.musik.id == rewardId }
        val result = if (index >= 0) {
            val musik = musikList[index]
            musikList[index] =
                musik.copy(musik = musik.musik, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getFavoriteMusik(): Flow<List<MusikList>> {
        return getMusik()
            .map { orderRewards ->
                orderRewards.filter { orderReward ->
                    orderReward.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: MusikRepository? = null

        fun getInstance(): MusikRepository =
            instance ?: synchronized(this) {
                MusikRepository().apply {
                    instance = this
                }
            }
    }
}