package se.gottfridn.library.identifier;


import se.gottfridn.library.logger.EncapsulatedLogger;
import se.gottfridn.library.logger.EncapsulatedLoggerFactory;

/**
 *	This class is a factory for creating instances of {@link ModIdentifier}.
 * <p>
 *     	This class provides the static methods
 * 		{@link ModIdentifierFactory#getIdentifier(String, Stability)} and
 * 		{@link ModIdentifierFactory#getIdentifier(String, String, Stability)}
 * 		for creating instances of {@link ModIdentifier} with specified {@link String} name and id.
 * </p><p>
 *     This has a built in logger that uses the {@link EncapsulatedLogger} api.
 * </p><p>Usage example:
 * <blockquote><pre>
 * ModIdentifier identifier = ModIdentifierFactory.getIdentifier("name");
 * String id = identifier.getId();
 * String name = identifier.getName();
 * </pre></blockquote>
 * or:
 * <blockquote><pre>
 * ModIdentifier identifier = ModIdentifierFactory.getIdentifier("name", "id");
 * String id = identifier.getId();
 * String name = identifier.getName();
 * </pre></blockquote></p>
 *
 * @apiNote The implementation for this is {@link ModIdentifierImplementation}
 *
 * @see ModIdentifier
 * @see ModIdentifierFactory#getIdentifier(String, Stability)
 * @see ModIdentifierFactory#getIdentifier(String, String, Stability)
 */

@SuppressWarnings("unused")
public class ModIdentifierFactory {
	private static final EncapsulatedLogger logger = EncapsulatedLoggerFactory.getLogger("mod_identifier_factory", true, false);

	/**
	 * Constructs a new {@link ModIdentifier} instance using {@link ModIdentifierFactory#getIdentifier(String, String, Stability)}
	 * with the specified {@code stability}, {@code name} and automatically generated {@code id}.
	 * <p>
	 *     The {@code id} is automatically generated by converting the {@code name} to {@literal snake_case}, this is done because it is Minecraft standard.
	 * </p>
	 *
	 * @param name The  {@code name} to be used. This is used to generate the {@code id}.
	 * @param stability The {@link Stability} level to be used.
	 *
	 * @return A new {@link ModIdentifier} instances with the given {@code stability}, {@code name} and automatically generated {@code id}.
	 *
	 * @see ModIdentifierImplementation
	 * @see ModIdentifierFactory#getIdentifier(String, String, Stability)
	 */
	public static ModIdentifier getIdentifier(String name, Stability stability) {
		// converts the name to snake_case
		final String id = name.toLowerCase().replaceAll("[^a-zA-Z0-9_]+", "_").replaceAll("_+", "_");
		return getIdentifier(name, id, stability);
	}

	/**
	 * Constructs a new {@link ModIdentifier} instance using {@link ModIdentifierImplementation}
	 * with the specified {@code name}, {@code id} and {@code stability}.
	 *
	 * @param name The  {@code name} to be used.
	 * @param id The {@code id} to be used.
	 * @param stability The {@code stability} of the mod, Stability levels use the {@link Stability} enum.
	 *
	 * @throws IllegalArgumentException If the {@code id} doesn't conform to the {@literal snake_case} format.
	 *
	 * @return A new {@link ModIdentifier} instances with the given {@code name} and {@code id}.
	 *
	 * @see ModIdentifierImplementation
	 * @see ModIdentifierFactory#getIdentifier(String, Stability)
	 */
	public static ModIdentifier getIdentifier(String name, String id, Stability stability) {
		// checks if id is in snake_case
		if (!id.matches("^[a-z0-9]+(_[a-z0-9]+)*$")) {
			logger.error("Illegal id: " + id + " doesn't conform to snake_case format!", new IllegalArgumentException());
		}
		return new ModIdentifierImplementation(name, id, stability);
	}

	/**
	 * The {@code ModIdentifierImplementation} class is an implementation of the interface {@link ModIdentifier}.
	 *
	 * @apiNote The {@code stability} uses the {@link Stability} enum.
	 *
	 * @see se.gottfridn.library.identifier.ModIdentifier
	 * @see ModIdentifierImplementation#ModIdentifierImplementation(String, String, Stability)
	 * @see se.gottfridn.library.identifier.ModIdentifierFactory#getIdentifier(String, String, Stability)
	 */
	static final class ModIdentifierImplementation
			implements ModIdentifier {
		private final String name;
		private final String id;
		private final Stability stability;

		/**
		 * Constructs a new {@code ModIdentifierImplementation} instance with the given {@code name}, {@code id} and {@code stability}.
		 *
		 * @param name The {@code name} of the {@code Identifier}.
		 * @param id The {@code id} of the {@code Identifier}.
		 * @param stability The {@code stability} of the {@code Identifier}.
		 *
		 * @see ModIdentifierImplementation
		 * @see ModIdentifierFactory
		 */
		public ModIdentifierImplementation(String name, String id, Stability stability) {
			this.name = name;
			this.id = id;
			this.stability = stability;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getStability() {
			return stability.stability;
		}
	}
}
