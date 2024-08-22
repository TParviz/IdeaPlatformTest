package tjrus.ideasplatformtest.domain.useCase

import kotlinx.coroutines.flow.flow
import tjrus.ideasplatformtest.data.repository.DatabaseRepository
import tjrus.ideasplatformtest.domain.db.itemInfo.ItemInfoEntity
import javax.inject.Inject

interface UpdateItemUseCase : FlowUseCase<UpdateItemParam, Unit>

class UpdateItemUseCaseImpl @Inject constructor(
    private val repository: DatabaseRepository
) : UpdateItemUseCase {

    override fun execute(param: UpdateItemParam) = flow {
        emit(Result.success(repository.updateItem(param.item)))
    }
}

data class UpdateItemParam(val item: ItemInfoEntity)