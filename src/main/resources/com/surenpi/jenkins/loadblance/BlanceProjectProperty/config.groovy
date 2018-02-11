package com.surenpi.jenkins.loadblance;

import static com.surenpi.jenkins.loadblance.BlanceProjectProperty.DescriptorImpl.NAME;

def f = namespace(lib.FormTagLib);

f.optionalBlock(name: NAME, title: _('github.project'), checked: instance != null) {
    f.entry(field: 'blance', title: _('github.project.url')) {
        f.textbox()
    }
}
