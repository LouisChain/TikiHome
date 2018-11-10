package hometest.android.tiki.domain.converter;

// Convert data from domain objects into other layers such as Data layer, App layer
// Because this data in each Layer is dependent
public interface DomainConverter<F, T> {
    T convert(F from);
}
