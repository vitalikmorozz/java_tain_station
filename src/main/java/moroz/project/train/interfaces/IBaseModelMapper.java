package moroz.project.train.interfaces;

public interface IBaseModelMapper<TEntity extends IBaseEntity, TRequestDTO extends IBaseDTO, TResponseDTO extends IBaseDTO> {
    TEntity convertFromRequestDTO(TRequestDTO dto);

    TResponseDTO convertToResponseDTO(TEntity entity);
}
