/*
 * This project was develope by Msc. Allan Gonçalves Gomes Oricil
 */

package Game_Library;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public enum GameStateEnum {
    PAUSED,  // if the state is paused, it means that the instance can return later 
    LOADING, // if the state is loading, it means that the instance is being loading in memory
    RUNNING, // if the state is running, it means that the instance is currently running
    STOPED   // if the state is stoped, it means that the instance no longer lives and has to be deleted
}
