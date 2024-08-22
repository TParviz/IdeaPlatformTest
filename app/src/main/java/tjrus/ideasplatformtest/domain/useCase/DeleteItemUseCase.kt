package tjrus.ideasplatformtest.domain.useCase

import kotlinx.coroutines.flow.flow
import tjrus.ideasplatformtest.data.repository.DatabaseRepository
import tjrus.ideasplatformtest.domain.db.itemInfo.ItemInfoEntity
import javax.inject.Inject

interface DeleteItemUseCase : FlowUseCase<DeleteItemParam, Unit>

class DeleteItemUseCaseImpl @Inject constructor(
    private val repository: DatabaseRepository
) : DeleteItemUseCase {

    override fun execute(param: DeleteItemParam) = flow {
        emit(Result.success(repository.deleteItem(param.item)))
    }
}

data class DeleteItemParam(val item: ItemInfoEntity)