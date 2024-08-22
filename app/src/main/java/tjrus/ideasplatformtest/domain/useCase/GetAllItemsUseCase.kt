package tjrus.ideasplatformtest.domain.useCase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tjrus.ideasplatformtest.data.repository.DatabaseRepository
import tjrus.ideasplatformtest.data.models.ItemInfo
import tjrus.ideasplatformtest.data.models.toUi
import javax.inject.Inject

interface GetAllItemsUseCase : FlowUseCase<Unit, List<ItemInfo>>

class GetAllItemsUseCaseImpl @Inject constructor(
    private val repository: DatabaseRepository
) : GetAllItemsUseCase {

    override fun execute(param: Unit): Flow<Result<List<ItemInfo>>> = flow {
        emit(Result.success(repository.getAllItems().map { it.toUi() }))
    }
}