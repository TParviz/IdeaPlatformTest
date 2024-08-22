package tjrus.ideasplatformtest.domain.useCase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tjrus.ideasplatformtest.data.repository.DatabaseRepository
import tjrus.ideasplatformtest.data.models.ItemInfo
import tjrus.ideasplatformtest.data.models.toUi
import javax.inject.Inject

interface SearchByNameUseCase : FlowUseCase<String, List<ItemInfo>>

class SearchByNameUseCaseImpl @Inject constructor(
    private val repository: DatabaseRepository
) : SearchByNameUseCase {

    override fun execute(query: String): Flow<Result<List<ItemInfo>>> = flow {
        emit(Result.success(repository.searchItemsByName(query).map { it.toUi() }))
    }
}