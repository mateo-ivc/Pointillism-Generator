package artcreator.statemachine.port;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface State {

	boolean isSubStateOf(State state);
	
	boolean isSuperStateOf(State state);

	public enum S implements State {

		CREATE_TEMPLATE, //
		/* more states */
		SELECT_IMAGE,
		EDIT_PARAMETERS,
		CHECK_IMAGE,


		SELECT_TEMPLATE,
		EDIT_TEMPLATE,
		SELECT_COLOR_PALETTE,
		GENERATE_TEMPLATE,
		GENERATE_PREVIEW,
		SAVE_TEMPLATE,
		ERROR_STATE
		;

		private List<State> subStates;

		public static final S INITIAL_STATE = SELECT_IMAGE;

		private S(State... subS) {
			this.subStates = new ArrayList<>(Arrays.asList(subS));
		}
		
		@Override
		public boolean isSuperStateOf(State s) {
			boolean result = (s == null) || (this == s); // self contained
			for (State state : this.subStates) // or
				result |= state.isSuperStateOf(s); // contained in a sub state!
			return result;
		}

		@Override
		public boolean isSubStateOf(State state) {
			return (state != null) && state.isSuperStateOf(this);
		}
	}

}