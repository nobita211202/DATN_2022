import axios from '@/node_modules/axios';

export const state = {
  currentUser: getSavedState('auth.currentUser'),
}

export const mutations = {
  SET_CURRENT_USER(state, newValue) {
    state.currentUser = newValue
    saveState('auth.currentUser', newValue)
    setDefaultAuthHeaders(state)
  },
}

export const getters = {
  // Whether the user is currently logged in.
  loggedIn(state) {
    return !!state.currentUser
  },
}

export const actions = {
  // This is automatically run in `src/state/store.js` when the app
  // starts, along with any other actions named `init` in other modules.
  init({ state, dispatch }) {
    setDefaultAuthHeaders(state)
    dispatch('validate')
  },

  // Logs in the current user.
  logIn({ commit, dispatch, getters }, acc = {}) {
    console.log(commit);
    console.log(dispatch);
    // if (getters.loggedIn) return dispatch('validate')

    return axios
      .post('/api/login', acc)
      .then((response) => {
        console.log(true);
        const user = response.data.value
        commit('SET_CURRENT_USER', user)

        return {
          status:true,
          data:user
        }
      })
      .catch(_=>{
        return {
          status:false,
          data:_.data
        }
      })
  },

  // Logs out the current user.
  logOut({ commit }) {
    commit('SET_CURRENT_USER', null)
  },

  // Validates the current user's token and refreshes it
  // with new data from the API.
  validate({ commit, state }) {
    if (!state.currentUser) return Promise.resolve(null)
    console.log(state.currentUser);
    console.log(axios.defaults.baseURL);
    // const url = `${Object.assign(axios.defaults.baseURL)}/api/logined/${state.currentUser.id}`
    return axios
      .get(`http://localhost:8888/api/logined/${state.currentUser.id}`)
      .then((response) => {
        const user = response.data
        commit('SET_CURRENT_USER', user)
        return user
      })
      .catch((error) => {
        if (error.response && error.response.status === 401) {
          commit('SET_CURRENT_USER', null)
        } else {
          console.warn(error)
        }
        return null
      })
  },
}

// ===
// Private helpers
// ===

function getSavedState(key) {
  return JSON.parse(window.localStorage.getItem(key))
}

function saveState(key, state) {
  window.localStorage.setItem(key, JSON.stringify(state))
}

function setDefaultAuthHeaders(state) {
  axios.defaults.headers.Authorization = state.currentUser
    ? state.currentUser.token
    : ''
}
