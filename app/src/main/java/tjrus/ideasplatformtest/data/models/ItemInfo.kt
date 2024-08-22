package tjrus.ideasplatformtest.data.models

import tjrus.ideasplatformtest.domain.db.itemInfo.ItemInfoEntity

data class ItemInfo(
    val id: Int,
    val name: String,
    val time: Long,
    val tags: List<String>,
    val amount: Int
)

fun ItemInfoEntity.toUi() = ItemInfo(
    id = id,
    name = name,
    time = time,
    tags = tags,
    amount = amount
)

fun ItemInfo.toEntity() = ItemInfoEntity(
    id = id,
    name = name,
    time = time,
    tags = tags,
    amount = amount
)