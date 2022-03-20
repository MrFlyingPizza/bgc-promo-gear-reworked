- Follow the User-Centered Design approach.
    - Group objects together if it makes sense from the user's perspective. For example, product and product variants 
      are although independent because they are strong entities, it still makes the most sense to treat product variants
      as the children of products. It may be natural to design the API in a way that it is separated by entities, but
      remember what it is used for. We use the API to display variants most definitely only when we display product as 
      well and not the other way around.
    - The API should serve the front-end which serves the users. If we expose entities through the API with 
      consideration on how it is used in designing user interfaces, front-end development can go much faster since it is
      primarily focused on the user experience. This means to focus on formatting including as much data as reasonable
      per the object groupings. Sending a list of product options for example is much easier and faster to do for the 
      backend than is for the frontend to create this list on every query.
- Package Grouping
    - All API functionality MUST belong to the `api` package and all view loading functionality must be ENTIRELY
      separated from the API.
    - Each exposed top-level objects must have its own package under `api`. Top-level objects are objects that are
      exposed to the user to be seen as existing independent of the existence of another object. For example,
      `api.products`, product is a top-level object by definition. These package names must be plural.
    - Each exposed object must have its own package, within this package contains its own `dto` package which contains
      `general` and `secured`. `general` is for public endpoint DTOs, `secured` is for secured endpoint DTOs.
    - Services should only be used by the respective controller and other services serving the same controller,
      therefore service methods must be only package-private or more strict unless it is a helper method.
    - Controller methods must be private because they are limited to handling API requests and returning responses
      only.
    - Repositories should be grouped in their respective top-level object packages for organization purposes and so
      are the entities, however, these should always be public because database access is universal to services.
    - DTOs are usually only used by the mapper for mapping them to entities to be used by services, therefore 
      construct packages in a way that only exposes DTOs' constructors to the respective mapper.
      For example, `api.product.secured.`
- Data Validation
    - Annotate `@Validated` at the class-level for controllers to ensure every DTO is validated.
    - All requests must be mapped to DTOs and DTOs are only validated when passed to the controller, before being passed
      to the respective service.
    - Every DTO must exclusively use annotation based bean validation.
    - Implement custom annotation constraint and respective constraint validators wherever needed, however, each
      constraint must only validate 1 field, and be reusable. Instead of defining `@ProductValid` to
      annotate the entire `ProductCreateDto`, we create DTOs only for the fields that need custom validation, for
      example, we can define `@CategoryExists` to be used on `categoryId` field. This way it is specific, and we can
      reuse the annotation whenever another category ID field needs to be validated.
- Data Transfer and Mapping
    - We must use `@Mapper` interfaces from MapStruct exclusively when outside DTO constructors.
    - Mappers should be able to convert between all DTOs and entity of the expose object.
    - DTOs are allowed to use composition, however, classes of objects in the composition must be defined as inner
      classes and follow the same rules as the outer class. This means inner classes of response DTOs must follow the
      same rules that the upper response DTO follows, for example, the rule below. Inner classes also have additional
      rules: they are only allowed to be constructed by the outer class constructor; they cannot have inner DTO classes;
      they can be composed by sibling classes.
    - Unenclosed request DTOs can only contain private final fields and private constructors that are marked with `@JsonCreator`.
      These objects are effectively read-only through getters. This is to prevent usages outside of passing data through
      the constructor. Enclosed DTOs follow the same rules, except their constructors where we allow the default access
      level so that it can be used by the enclosing DTO.
    - Response DTOs' constructors must be able to take an Entity and convert it to the response DTO, however, like
      mentioned before, this functionally is reserved for mappers.
    - Request DTOs must be read only, therefore final with only getters.
    - Don't use collections as response objects. Create DTOs instead.
    - Response DTOs that represent the exposed object can be reused in other DTOs ONLY in batch response objects. For
      example, GET requests that gets multiple objects have its own response DTO that contains a collection of said
      object.
    - Create a mapper for each object public and secured endpoints. For example `api/secured/product` has its own
      mapper, `api/product` has its own mapper, `api/product/{productId}/variant` has its own mapper; though
      `api/product/{productId}` doesn't have its own mapper because it is exposing the same product object as
      `api/product`.
- Controller and Service Design
    - Each top-level object public controller must have a `@RequestMapping` of the following form. 
      `@RequestMapping("api/[top_level_objects]")`
      Each top-level object secured controller must have a `@RequestMapping` of the following form.
      `@RequestMapping("api/secured/[top_level_objects]")`
      Each lower-level object public controller must have a `@RequestMapping` of the following form.
      `@RequestMapping("api/secured/[top_level_objects]/{[top_level_object_id_path_variable]}/[lower_level_objects]")`
      Replace [upper_level_objects] with the name of the object, MUST be plural. Same idea for [lower_level_objects].
      Replace [top_level_object_id_path_variable] with the name of the path variable of the object, for example,
      `productId`.
      Subsequent lower-level objects have the following form.
      `@RequestMapping("api/secured/[top_level_objects]/{[top_level_object_id_path_variable]}/[lower_level_objects]/{[lower_level_object_id_path_variable]}/[lower_level_objects]/{[lower_level_object_id_path_variable]}/...so on")`
    - There must be one and only one separate controller and at least one service for each exposed lower-level object.
      Lower-level objects are objects whose exposure depends on that of the higher-level object. For example, product
      variant. The controller and at least 1 service is responsible for that expose object ONLY, meaning no higher-level
      objects or lower-level objects should be managed by this controller and service.
    - Controllers cannot return response DTOs that represent objects that are higher level or lower level than the
      object that they are responsible for. For example, product option controller cannot return representations of
      product, and cannot return presentations of option value unless option value is part of a composition.
    - Treat services as if they have no idea about the security that is being implemented by the controller. All
      that a service should care about is executing a set of business logic and operate on the database regardless of
      the responsibilities of the caller controller.
    - Secured controller begin with `Secured` and public controllers begin with `General`.