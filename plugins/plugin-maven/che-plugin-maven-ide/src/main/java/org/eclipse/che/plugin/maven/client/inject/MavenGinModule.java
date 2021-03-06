/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.plugin.maven.client.inject;

import static com.google.gwt.inject.client.multibindings.GinMultibinder.newSetBinder;

import com.google.gwt.inject.client.AbstractGinModule;
import org.eclipse.che.api.languageserver.shared.model.LanguageDescription;
import org.eclipse.che.ide.api.command.CommandType;
import org.eclipse.che.ide.api.extension.ExtensionGinModule;
import org.eclipse.che.ide.api.preferences.PreferencePagePresenter;
import org.eclipse.che.ide.api.project.type.wizard.ProjectWizardRegistrar;
import org.eclipse.che.ide.api.resources.ResourceInterceptor;
import org.eclipse.che.ide.project.ResolvingProjectStateHolder;
import org.eclipse.che.plugin.maven.client.MavenLanguageDescriptionProvider;
import org.eclipse.che.plugin.maven.client.command.MavenCommandType;
import org.eclipse.che.plugin.maven.client.preference.MavenPreferencePresenter;
import org.eclipse.che.plugin.maven.client.project.ResolvingMavenProjectStateHolder;
import org.eclipse.che.plugin.maven.client.resource.MavenProjectInterceptor;
import org.eclipse.che.plugin.maven.client.resource.MavenSourceFolderInterceptor;
import org.eclipse.che.plugin.maven.client.resource.PomInterceptor;
import org.eclipse.che.plugin.maven.client.wizard.MavenProjectWizardRegistrar;

/**
 * GIN module for Maven extension.
 *
 * @author Andrey Plotnikov
 * @author Artem Zatsarynnyi
 */
@ExtensionGinModule
public class MavenGinModule extends AbstractGinModule {

  @Override
  protected void configure() {
    newSetBinder(binder(), ProjectWizardRegistrar.class)
        .addBinding()
        .to(MavenProjectWizardRegistrar.class);

    newSetBinder(binder(), CommandType.class).addBinding().to(MavenCommandType.class);

    newSetBinder(binder(), PreferencePagePresenter.class)
        .addBinding()
        .to(MavenPreferencePresenter.class);

    newSetBinder(binder(), ResourceInterceptor.class)
        .addBinding()
        .to(MavenSourceFolderInterceptor.class);
    newSetBinder(binder(), ResourceInterceptor.class).addBinding().to(PomInterceptor.class);
    newSetBinder(binder(), ResourceInterceptor.class)
        .addBinding()
        .to(MavenProjectInterceptor.class);

    newSetBinder(binder(), ResolvingProjectStateHolder.class)
        .addBinding()
        .to(ResolvingMavenProjectStateHolder.class);

    newSetBinder(binder(), LanguageDescription.class)
        .addBinding()
        .toProvider(MavenLanguageDescriptionProvider.class);
  }
}
