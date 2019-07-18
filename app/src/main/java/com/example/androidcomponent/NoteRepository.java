package com.example.androidcomponent;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

     void insetNote(Note note) {

        new InsetNoteAsyncTask(noteDao).execute(note);
    }

     void updateNote(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);

    }

     void deleteNote(Note note) {

        new DeleteNoteAsyncTask(noteDao).execute(note);

    }

     void deleteAllNote() {
        new DeleteAllNoteAsyncTask(noteDao).execute();


    }

     LiveData<List<Note>> getAllNote() {

        return allNotes;
    }

    private static class InsetNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        InsetNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;

        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.insertNote(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;

        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.updateNote(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;

        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.deleteNote(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        DeleteAllNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;

        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.deleteAllNotes();
            return null;
        }
    }
}
